package com.example.appteam4.ui.viewmodel

class ImagesViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {

    private val _data = MutableLiveData<StateProductById>()
    val data: LiveData<StateProductById> = _data

    fun getSimilarProducts(id: Int) {
        viewModelScope.launch {
            val response = repository.getProductById(id)
            _data.postValue(StateProductById.Loading)
            if (response.isSuccessful) {
                response.body()?.let {
                    _data.postValue(StateProductById.Success(it))
                } ?: _data.postValue(StateProductById.Error(Constants.PRODUCTS_FAILED))
            } else {
                _data.postValue(StateProductById.Error(Constants.NETWORK_ERROR))
            }
        }
    }

}
