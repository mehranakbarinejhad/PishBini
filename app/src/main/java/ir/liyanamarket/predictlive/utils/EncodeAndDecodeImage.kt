package ir.liyanamarket.predictlive.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream
import java.lang.Exception
class EncodeAndDecodeImage {

    fun decodeimage(strimage:String,imageView: ImageView)
    {
        val imagebyte= Base64.decode(strimage, Base64.DEFAULT)
        val decodeimage= BitmapFactory.decodeByteArray(imagebyte,0,imagebyte.size)
        imageView.setImageBitmap(decodeimage)
    }

    fun encodeimage(activity: AppCompatActivity, path: Uri):String
    {
        var strimage=""
        try {
            val bitmap= MediaStore.Images.Media.getBitmap (activity.contentResolver,path)
            val byteArrayOutputStream= ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,40,byteArrayOutputStream)
            val imagetobyte=byteArrayOutputStream.toByteArray()
            strimage= Base64.encodeToString(imagetobyte, Base64.DEFAULT)

        }
        catch (ex: Exception){

        }
        return strimage
    }
}