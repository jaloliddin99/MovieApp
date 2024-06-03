package org.don.onlineTrade.utils.runTimePermission

interface OnRunTimePermissionListener {

    //onPermission Granted...
    fun onPermissionGranted()

    //onPermissionDenied
    fun onPermissionDenied()
}