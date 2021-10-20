package com.example.shopping.Buyers.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.Buyers.ConfirmFinalOrder;
import com.example.shopping.Buyers.HomeActivity;
import com.example.shopping.Model.Cart;
import com.example.shopping.Prevalent.Prevalent;
import com.example.shopping.Buyers.ProductDetailsActivity;
import com.example.shopping.R;
import com.example.shopping.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

public class CartFragment extends Fragment {
 RecyclerView recyclerView;


RecyclerView.LayoutManager layoutManager;
    private DatabaseReference cartListRef;
    private int overTotalPrice=0;
   MaterialButton btnNextCart;
   TextView tvTotalPrice;

    Button b1,b2,b3;
    String sms;

    public CartFragment(){}


    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView =root.findViewById(R.id.rv_cart);
        btnNextCart=root.findViewById(R.id.btn_cart);
        tvTotalPrice=root.findViewById(R.id.total_price_cart);

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        b1=root.findViewById(R.id.listen);
        b2=root.findViewById(R.id.call);
        b3=root.findViewById(R.id.sms);


           cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

           btnNextCart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   tvTotalPrice.setText("Total Price = "+String.valueOf(overTotalPrice)+" LE");

                   Intent intent=new Intent(getActivity(), ConfirmFinalOrder.class);
                   intent.putExtra("Total Price",String.valueOf(overTotalPrice));
                   startActivity(intent);
                   getActivity().finish();
               }
           });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String toSpeak = mRecyclerView.getText().toString();
                t1.setPitch(0.8f);
                t1.setSpeechRate(0.8f);

                //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }*/
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+967738052676";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("smsto:"));
                    i.setType("vnd.android-dir/mms-sms");
                    i.putExtra("address", new String("967738052676"));
                    i.putExtra("sms_body",sms);
                    startActivity(Intent.createChooser(i, "Send sms via:"));
                }
                catch(Exception e){
                  //  Toast.makeText(CartFragment.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    public void paymentClick(View pressed) {

        // PAYMENT_INTENT_SALE will cause the payment to complete immediately.
        // Change PAYMENT_INTENT_SALE to
        //   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
        //   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
        //     later via calls from your server.

      //  PayPalPayment payment = new PayPalPayment(new BigDecimal(totalPrice), "USD", "Being payment for items ordered" ,
        //        PayPalPayment.PAYMENT_INTENT_SALE);

       // Intent intent = new Intent(this, PaymentActivity.class);

        // send the same configuration for restart resiliency
       // intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

       // intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

       // startActivityForResult(intent, 0);
    }


    public void call(View view)
    {
        String phone = "+0123456789";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }



    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options
                =new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View")
                .child(Prevalent.currentOnlineUser.getPhone()).child("Products"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {

                        holder.CartProductName.setText(model.getName());
                           holder.CartProductPrice.setText("Price: "+model.getPrice());
                           holder.CartProductQuantity.setText("Quantity= "+model.getQuantity());
                        //  holder.CartProductImage.setText("Image: "+model.getImage());
                        Picasso.get().load(model.getImage()).into(holder.CartProductImage);
                        //  holder.CartProductImage.setText(model.getImage());

                           int oneTypeProductPrice=((Integer.valueOf(model.getPrice()))*(Integer.valueOf(model.getQuantity())));
                           overTotalPrice=overTotalPrice + oneTypeProductPrice;

                           holder.itemView.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   CharSequence options[]=new CharSequence[]
                                           {
                                                   "Edit",
                                                   "Remove"
                                           };

                                   AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                   builder.setTitle("Cart Options: ");
                                   builder.setItems(options, new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {

                                           if (which==0){
                                               Intent intent=new Intent(getActivity(), ProductDetailsActivity.class);
                                               intent.putExtra("pid",model.getPid());
                                               startActivity(intent);
                                           }

                                           if (which==1)
                                           {
                                           cartListRef.child("User View")
                                           .child(Prevalent.currentOnlineUser.getPhone())
                                           .child("Products")
                                           .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()){
                                                        Toast.makeText(getActivity(), "Item is deleted", Toast.LENGTH_SHORT).show();
                                                        Intent intent=new Intent(getActivity(), HomeActivity.class);
                                                        startActivity(intent);


                                                    }


                                                }
                                            });

                                           }

                                       }
                                   });
                                   builder.show();

                               }
                           });

                    }



                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                        CartViewHolder holder=new CartViewHolder(view);
                        return holder;


                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}