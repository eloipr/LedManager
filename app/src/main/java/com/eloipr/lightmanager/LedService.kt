package com.eloipr.lightmanager

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest

class LedService : ServiceInterface {
    val TAG = LedService::class.java.simpleName
    val basePath = "http://192.168.1.76/"

    override fun post(path: String, completionHandler: (response: String?) -> Unit) {
        val jsonObjReq = object : StringRequest(Method.POST, basePath + path,
            Response.Listener<String> { response ->
                Log.d(TAG, "/post request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->
                VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                completionHandler(null)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        Backend.instance?.addToRequestQueue(jsonObjReq)
    }
}