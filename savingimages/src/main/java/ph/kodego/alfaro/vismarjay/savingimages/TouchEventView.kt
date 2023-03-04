package ph.kodego.alfaro.vismarjay.savingimages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import java.io.File
import java.io.FileOutputStream
import java.util.*

private const val STROKE_WIDTH = 12f

class TouchEventView (context: Context, attrs: AttributeSet): AppCompatImageView(context, attrs){

    private var path = Path()
    private val drawColor = ResourcesCompat.getColor(resources,R.color.colorPaint,null)
    

    companion object {
        private val path = Path()
        private var localContext: Context? = null
    }

    private val paint = Paint()
    private var gestureDetector: GestureDetector? = null
    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas

    init{
        localContext = context
        gestureDetector =
            GestureDetector(context, GestureListener())

        paint.isAntiAlias = true
        paint.strokeWidth = 6f
        paint.color = Color.LTGRAY

        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND

        bitmap = createBitmap(width,height,Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    private class GestureListener : GestureDetector.SimpleOnGestureListener(){
        //event when double tap occurs
        override fun onDoubleTap(e:MotionEvent): Boolean{
            val x = e.x
            val y = e.y
            path.reset()
            Toast.makeText(localContext, "Double Tap>> Tapped at: ($x,$y)",Toast.LENGTH_SHORT).show()
            return true
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val eventX = event?.x
        val eventY = event?.y
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(eventX!!,eventY!!)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(eventX!!,eventY!!)
            MotionEvent.ACTION_UP -> {}
            else -> return false
        }
        gestureDetector!!.onTouchEvent(event)
        invalidate()
        return true
    }

}
