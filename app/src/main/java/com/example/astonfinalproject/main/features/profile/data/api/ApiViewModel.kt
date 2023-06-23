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

class ApiViewModel @Inject constructor(): ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    val characterMutableLiveData = MutableLiveData<Character>()
    val episodeMutableLiveData = MutableLiveData<Episode>()
    val locationMutableLiveData = MutableLiveData<Location>()
    val itemCharacterMutableLiveData = MutableLiveData<List<
            com.example.astonfinalproject.main.features.profile.data.dto.character.Result
            >>()
    val itemEpisodeMutableLiveData = MutableLiveData<List<
            com.example.astonfinalproject.main.features.profile.data.dto.episode.Result
            >>()
    val itemLocationMutableLiveData = MutableLiveData<List<
            com.example.astonfinalproject.main.features.profile.data.dto.location.Result
            >>()

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

    fun getItemCharacter(characterNames: String,itemCharacterLiveData: MutableLiveData<String>){
        compositeDisposable.add(takeRepo.getResponseItemCharacter(characterNames)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<com.example.astonfinalproject.main.features.profile.data.dto.character.Result>>(){
                override fun onSuccess(characterResponse: List<com.example.astonfinalproject.main.features.profile.data.dto.character.Result>) {
                    itemCharacterMutableLiveData.value = characterResponse
                    val characters = characterResponse.joinToString(", ") { it.name }
                    itemCharacterLiveData.value = characters
                    Log.d("LOGTAG", " onSuccess $characterResponse")
                }
                override fun onError(e: Throwable) {
                    Log.e("LOGTAG", "onError $e")
                }
            })
        )
    }

    fun getItemEpisode(episodeNumber: String){
        compositeDisposable.add(takeRepo.getResponseItemEpisode(episodeNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<com.example.astonfinalproject.main.features.profile.data.dto.episode.Result>>(){
                override fun onSuccess(characterResponse: List<com.example.astonfinalproject.main.features.profile.data.dto.episode.Result>) {
                    itemEpisodeMutableLiveData.value = characterResponse
                    Log.d("LOGTAG", "onSuccess $characterResponse")
                }
                override fun onError(e: Throwable) {
                    Log.e("LOGTAG", "onError $e")
                }
            })
        )
    }

    fun getItemLocation(locationNames: String){
        compositeDisposable.add(takeRepo.getResponseItemLocation(locationNames)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<com.example.astonfinalproject.main.features.profile.data.dto.location.Result>>(){
                override fun onSuccess(characterResponse: List<com.example.astonfinalproject.main.features.profile.data.dto.location.Result>) {
                    itemLocationMutableLiveData.value = characterResponse
                    Log.d("LOGTAG", "get Item Location onSuccess $characterResponse")
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


