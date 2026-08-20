package com.echcoding.myfirebasechatbot.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RoomRepository(private val firestore: FirebaseFirestore) {

    // Save the room to the firestore
    suspend fun createRoom(name: String): Result<Unit> = try {
        val room = Room(name = name)
        firestore.collection("rooms").add(room).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    // Get all the rooms from the firestore
    suspend fun getRooms(): Result<List<Room>> = try {
        val querySnapshot = firestore.collection("rooms").get().await()
        val rooms = querySnapshot.documents.mapNotNull {
            it.toObject(Room::class.java)!!.copy(id = it.id)
        }
        Result.Success(rooms)
    } catch (e: Exception) {
        Result.Error(e)
    }


}