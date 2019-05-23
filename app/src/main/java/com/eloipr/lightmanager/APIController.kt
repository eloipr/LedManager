package com.eloipr.lightmanager

class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {
    private val service: ServiceInterface = serviceInjection

    override fun post(path: String, completionHandler: (response: String?) -> Unit) {
        service.post(path, completionHandler)
    }
}