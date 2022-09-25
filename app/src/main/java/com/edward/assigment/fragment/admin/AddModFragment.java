package com.edward.assigment.fragment.admin;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edward.assigment.R;

public class AddModFragment extends Fragment {
    ImageView imageView;
    TextView name, id, pass;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_mod_fragment, container, false);
        initView();
        initToggleButton();
        return view;
    }

    private void initView() {
        name = view.findViewById(R.id.modname);
        id = view.findViewById(R.id.modid);
        pass = view.findViewById(R.id.modPass);
        imageView = view.findViewById(R.id.togglePass);
    }

    private void initToggleButton() {

        imageView.setOnClickListener(view -> {
            Integer integer = (Integer) imageView.getTag();
            integer = integer == null ? 0 : integer;

            switch (integer) {
                case R.drawable.visible_eye:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.invisible_eye));
                    imageView.setTag(R.drawable.invisible_eye);
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    break;
                case R.drawable.invisible_eye:
                default:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.visible_eye));
                    imageView.setTag(R.drawable.visible_eye);
                    pass.setInputType(InputType.TYPE_NULL);
                    break;
            }
        });
    }
}
