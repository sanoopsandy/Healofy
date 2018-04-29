package com.example.sanoop.healofy.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sanoop.healofy.models.MusicInfo

class DBHelper(context: Context) : SQLiteOpenHelper(context, "music_db", null, 1), IMusicCacheDAO {

    val TABLE_NAME: String = "music_info"
    val COL_ID: String = "_id"
    val COL_MUSIC_NAME: String = "name"
    val COL_MUSIC_ARTIST: String = "artist"
    val COL_MUSIC_ALBUM: String = "album"


    override fun onCreate(db: SQLiteDatabase?) {
        val createMusicTable = "CREATE TABLE ${TABLE_NAME} (${COL_ID}  INTEGER PRIMARY KEY AUTOINCREMENT, ${COL_MUSIC_NAME}  TEXT NOT NULL, ${COL_MUSIC_ARTIST} TEXT, ${COL_MUSIC_ALBUM} TEXT)"
        Log.d("Alpha", "Table created  $TABLE_NAME")
        db?.execSQL(createMusicTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.close()
    }

    override fun saveMusicList(musicList: List<MusicInfo>) {
        val db = this.writableDatabase
        val values = ContentValues()
        musicList.forEach {
            values.put(COL_MUSIC_ALBUM, it.album)
            values.put(COL_MUSIC_ARTIST, it.artist)
            values.put(COL_MUSIC_NAME, it.name)
            db.insert(TABLE_NAME, null, values)
        }
        db.close()

    }

    override fun getMusicList(): List<MusicInfo> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val musicList = ArrayList<MusicInfo>()
        if (cursor.count > 0){
            while (cursor.moveToNext()){
                val musicInfo = MusicInfo()
                musicInfo.name = cursor.getString(cursor.getColumnIndex(COL_MUSIC_NAME))
                musicInfo.artist = cursor.getString(cursor.getColumnIndex(COL_MUSIC_ARTIST))
                musicInfo.album= cursor.getString(cursor.getColumnIndex(COL_MUSIC_ALBUM))
                musicList.add(musicInfo)
            }
        }
        cursor.close()
        return musicList
    }

    override fun searchMusic(query: String): List<MusicInfo> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_MUSIC_NAME LIKE ? OR $COL_MUSIC_ALBUM LIKE ? OR $COL_MUSIC_ARTIST LIKE ?", arrayOf("%$query%", "%$query%", "%$query%"))
        val musicList = ArrayList<MusicInfo>()
        if (cursor.count > 0){
            while (cursor.moveToNext()){
                val musicInfo = MusicInfo()
                musicInfo.name = cursor.getString(cursor.getColumnIndex(COL_MUSIC_NAME))
                musicInfo.artist = cursor.getString(cursor.getColumnIndex(COL_MUSIC_ARTIST))
                musicInfo.album= cursor.getString(cursor.getColumnIndex(COL_MUSIC_ALBUM))
                musicList.add(musicInfo)
            }
        }
        cursor.close()
        return musicList
    }

}