package com.eloipr.lightmanager

interface ServiceInterface {
    fun post(path: String, completionHandler: (response: String?) -> Unit)
}