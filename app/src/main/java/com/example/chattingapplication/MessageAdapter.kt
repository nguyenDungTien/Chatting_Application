package com.example.chattingapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var item_receive=1
    var item_sent=2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==1){
            //inflate receive
            val view:View= LayoutInflater.from(context).inflate(R.layout.receive,parent, false)
            return ReceiveViewHolder(view)
        }else{
            //inflate sent
            val view:View=LayoutInflater.from(context).inflate(R.layout.sent,parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currenMessage = messageList[position]
        if(holder.javaClass==SentViewHolder::class.java ){
            //do the stuff for sent view holder
            var viewHolder =holder as SentViewHolder
            holder.sentMessage.text=currenMessage.message
        }else{
            //do the stuff receive view holder
            var viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text=currenMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        var currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return item_sent
        }else{
            return item_receive
        }
    }

    override fun getItemCount(): Int {
        return messageList.size

    }
    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sentMessage = itemView.findViewById<TextView>(R.id.txtSendMesssage)

    }
    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var receiveMessage = itemView.findViewById<TextView>(R.id.txtReceiveMesssage)
    }
}