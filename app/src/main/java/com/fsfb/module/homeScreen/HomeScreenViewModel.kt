package com.fsfb.module.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fsfb.common.BaseViewModel
import com.fsfb.database.RoomDao
import com.fsfb.database.StoredData
import com.fsfb.module.dto.DataClass
import com.fsfb.module.homeScreen.adapter.ButtonState
import com.fsfb.repository.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeScreenRepository: HomeScreenRepository,
    private val roomDao: RoomDao
) : BaseViewModel() {

    var componentList = MutableLiveData<ArrayList<DataClass>>()
    var noRecordFound = MutableLiveData<Boolean>()

    fun getHomeScreenData() {

        viewModelScope.launch {

            isBusy.value = true
            val result = homeScreenRepository.getHomeScreenData()
            isBusy.value = false

            result.onSuccess {
                componentList.value = checkWithExistingData(it)
                noRecordFound.value = componentList.value!!.size == 0
            }
            result.onFailure { exception ->
                exception.localizedMessage?.let {
                    showToast(it)
                }
            }
        }
    }

    private fun checkWithExistingData(data: ArrayList<DataClass>): ArrayList<DataClass>{
        if(!roomDao.getAllData().isNullOrEmpty()){
            for(dataClass in data){
                dataClass.email?.let {
                    val roomData = roomDao.getData(it)
                    if(roomData != null){
                        dataClass.isAccept = roomData.isAccept
                        dataClass.isDeclined = roomData.isDeclined
                    }
                }
            }
        }
        return data
    }

    fun updateData(dataClass: DataClass, btnState: ButtonState) {
        dataClass.email?.let {
            val storedData = roomDao.getData(it)
            if (storedData != null) {
                if (btnState.state == ButtonState.ACCEPT.state) {
                    storedData.isAccept = true
                } else {
                    storedData.isDeclined = true
                }
                roomDao.updateData(storedData)
            } else {
                roomDao.insertData(StoredData(
                    email = it, isAccept = btnState.state == ButtonState.ACCEPT.state,
                    isDeclined = btnState.state == ButtonState.DECLINE.state
                ))
            }

        }

    }
}