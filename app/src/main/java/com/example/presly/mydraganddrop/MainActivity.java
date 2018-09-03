package com.example.presly.mydraganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView ima;
    private static final String IMAGEVIEW_TAG = "Android Logo";
    String msg;

    private android.widget.RelativeLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ima = (ImageView)findViewById(R.id.iv_logo);
        //set the tag
        ima.setTag(IMAGEVIEW_TAG);

        ima.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);

                //instantiate the drag shadow builder
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(ima);

                //starts the drag
                v.startDragAndDrop(dragData,myShadow,null,0);

                return true;
            }
        });

        //create and set drag event listener for the view
        ima.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
              switch(event.getAction()){
                  case DragEvent.ACTION_DRAG_STARTED:
                      layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                      Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                      //do nothing
                      break;
                      case DragEvent.ACTION_DRAG_ENTERED:
                          Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                          int x_cord = (int)event.getX();
                          int y_cord = (int)event.getY();
                          break;

                          case DragEvent.ACTION_DRAG_EXITED:
                              Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                              x_cord = (int)event.getX();
                              y_cord = (int)event.getY();
                              layoutParams.leftMargin = x_cord;
                              layoutParams.topMargin = y_cord;
                              v.setLayoutParams(layoutParams);
                              break;

                              case DragEvent.ACTION_DRAG_LOCATION:
                                  Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                                  x_cord = (int)event.getX();
                                  y_cord = (int)event.getY();
                                  break;

                                  case DragEvent.ACTION_DRAG_ENDED:
                                      Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                                      break;

                                      default:break;
              }
            return true;
            }
        });
    }
}
