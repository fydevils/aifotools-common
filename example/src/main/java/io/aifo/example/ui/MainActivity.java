package io.aifo.example.ui;

import android.Manifest;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.aifo.api.aspect.Permission;
import io.aifo.api.aspect.SingleClick;
import io.aifo.api.javassist.Inject;
import io.aifo.example.R;
import io.aifo.example.entity.Persion;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private String a = null;

    @Inject
    Persion persion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = this.findViewById(R.id.tv_hello);
        viewById.setOnClickListener(this);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
      takeAPicture();
    }

    @Permission(Manifest.permission.CAMERA)
    private void takeAPicture() {
        persion.school.printSchool();
    }


}
