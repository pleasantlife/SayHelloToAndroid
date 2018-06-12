package com.gandan.android.kotlinfb

import android.util.Log
import java.io.File

/**
 * Created by XPS on 2018-05-28.
 */
interface GetWriteDataListener {
    fun contentOne(thingOne : String)
    fun imageOne(fileOne : File)
    fun contentTwo(thingTwo : String)
    fun imageTwo(fileTwo : File)
    fun contentThree(thingThree : String)
    fun imageThree(fileThree : File)
}