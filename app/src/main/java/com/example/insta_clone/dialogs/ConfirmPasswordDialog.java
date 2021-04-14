package com.example.insta_clone.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.insta_clone.R;

public class ConfirmPasswordDialog extends DialogFragment {


    public interface OnConfirmPasswordListener
    {
        public void onConfirmPassword(String password);
    }

    OnConfirmPasswordListener onConfirmPasswordListener;


    TextView mPasseord;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_confirm_password, container, false);
        mPasseord = view.findViewById(R.id.confirmPassword);


        TextView cancelDialog = view.findViewById(R.id.dialogCancel);
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        TextView confirmDialog = view.findViewById(R.id.dialogConfirm);
        confirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password  = mPasseord.getText().toString();
                if (!password.equals("")) {
                    onConfirmPasswordListener.onConfirmPassword(password);
                    getDialog().dismiss();
                }else {
                    Toast.makeText(getActivity(), "You must enter password", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            onConfirmPasswordListener = (OnConfirmPasswordListener) getTargetFragment();
        }

        catch (ClassCastException e)
        {

        }
    }
}
