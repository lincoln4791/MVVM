package com.example.mymvvmstructure.example1

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymvvmstructure.example1.modelClasses.User

/**
 * Created by Soumik on 11,October,2020
 * ITmedicus,
 * Dhaka, Bangladesh.
 */
class SearchRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    companion object {
        private const val TAG = "Search"
    }

    suspend fun readData(): LiveData<Resource<ArrayList<User>>> {
        val liveData = MutableLiveData<Resource<ArrayList<User>>>()

        liveData.postValue(Resource.loading())

        //dataAdapter.open()
        try {
            val result = dbHelper.readData()
            liveData.postValue(Resource.success(result))
        } catch (e: Exception) {
            Log.e(TAG, "Exception occurred in Hospital Search: ${e.localizedMessage}")
            liveData.postValue(Resource.error(e.localizedMessage))
        } finally {
            //dataAdapter.close()
        }

        return liveData
    }

/*    suspend fun ambulanceSearch(query: String,district: String?,area: String?):LiveData<Resource<ArrayList<AmbulanceEvent>>> {
        val liveData = MutableLiveData<Resource<ArrayList<AmbulanceEvent>>>()

        liveData.postValue(Resource.loading())

        dataAdapter.open()

        try {
            val result = dataAdapter.searchAmbulance(query,district, area)
            liveData.postValue(Resource.success(result))
        } catch (e:Exception) {
            Log.e(TAG,"Exception occurred in Ambulance Search: ${e.localizedMessage}")
            liveData.postValue(Resource.error(e.localizedMessage))
        } finally {
            dataAdapter.close()
        }

        return liveData
    }

    suspend fun pharmacySearch(query: String,district: String?,area: String?):LiveData<Resource<ArrayList<DrugStoreEvent>>> {
        val liveData = MutableLiveData<Resource<ArrayList<DrugStoreEvent>>>()

        liveData.postValue(Resource.loading())
        dataAdapter.open()
        try {
            val result = dataAdapter.searchDrugStore(query,district, area)
            liveData.postValue(Resource.success(result))
        } catch (e:Exception) {
            Log.e(TAG,"Exception occurred in Pharmacy Search: ${e.localizedMessage}")
            liveData.postValue(Resource.error(e.localizedMessage))
        } finally {
            dataAdapter.close()
        }

        return liveData
    }

    suspend fun bloodBankSearch(query: String,district: String?,area: String?):LiveData<Resource<ArrayList<BloodbankEvent>>> {
        val liveData = MutableLiveData<Resource<ArrayList<BloodbankEvent>>>()

        liveData.postValue(Resource.loading())
        dataAdapter.open()
        try {
            val result = dataAdapter.searchBloodBank(query,district, area)
            liveData.postValue(Resource.success(result))
        } catch (e:Exception) {
            Log.e(TAG,"Exception occurred in BloodBank Search: ${e.localizedMessage}")
            liveData.postValue(Resource.error(e.localizedMessage))
        } finally {
            dataAdapter.close()
        }

        return liveData
    }*/


}