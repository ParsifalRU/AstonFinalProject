package com.example.astonfinalproject.main.features.profile.data.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.episode.Episode
import com.example.astonfinalproject.main.features.profile.data.dto.location.Location
import com.example.astonfinalproject.main.network.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ApiViewModel @Inject constructor(
   /* private val apiRepository: ApiRepository*/
): ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    val characterMutableLiveData = MutableLiveData<Character>()
    val episodeMutableLiveData = MutableLiveData<Episode>()
    val locationMutableLiveData = MutableLiveData<Location>()


    private val takeRepo = ApiRepository(NetworkModule().provideRetrofit().create(Api::class.java))
    private val takeCharacters = takeRepo.getResponseCharacter()
    private val takeEpisodes = takeRepo.getResponseEpisode()
    private val takeLocations = takeRepo.getResponseLocation()

    fun getCharacters(){
            compositeDisposable.add(takeCharacters
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<Character>(){
                    override fun onSuccess(characterResponse: Character) {
                        characterMutableLiveData.value = characterResponse
                        Log.d("LOGTAG", "onSuccess $characterResponse")
                    }
                    override fun onError(e: Throwable) {
                        Log.e("LOGTAG", "onError $e")
                    }
                })
            )
    }

    fun getEpisodes(){
        compositeDisposable.add(takeEpisodes
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Episode>(){
                override fun onSuccess(episodeResponse: Episode) {
                    episodeMutableLiveData.value = episodeResponse
                    Log.d("LOGTAG", "onSuccess $episodeResponse")
                }
                override fun onError(e: Throwable) {
                    Log.e("LOGTAG", "onError $e")
                }
            })
        )
    }

    fun getLocations(){
        compositeDisposable.add(takeLocations
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Location>(){
                override fun onSuccess(locationResponse: Location) {
                    locationMutableLiveData.value = locationResponse
                    Log.d("LOGTAG", "onSuccess $locationResponse")
                }
                override fun onError(e: Throwable) {
                    Log.e("LOGTAG", "onError $e")
                }
            })
        )
    }



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}


