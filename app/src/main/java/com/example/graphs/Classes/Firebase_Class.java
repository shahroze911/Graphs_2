package com.example.graphs.Classes;

import com.example.graphs.Pie_Chart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase_Class {

    static public FirebaseDatabase firebaseDatabase;
    static public DatabaseReference  databaseReference;
    static public String User_key;
    static public Graph graph;
    static public double INCOME;
    static public double PROFIT;
    public Firebase_Class()
    {

    }

    public void send_data(String mesg)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Graphs");
        databaseReference.child("Pie Chart").child("Profit").setValue("10000");
    }
    public void send_data(Graph name,String user, String month,String day)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Graphs");
        databaseReference.child("Pie Chart").child(user).child(month).child(day).setValue(name);
    }
    public void get_profit_and_income(String user, String month, String day, final Pie_Chart.OnGetDataListener listener)
    {
        listener.onStart();
        databaseReference = FirebaseDatabase.getInstance().getReference("Graphs");
        databaseReference = databaseReference.child("Pie Chart").child(user).child(month).child(day);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               listener.onSuccess(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        };
        databaseReference.addListenerForSingleValueEvent(eventListener);
    }


}
