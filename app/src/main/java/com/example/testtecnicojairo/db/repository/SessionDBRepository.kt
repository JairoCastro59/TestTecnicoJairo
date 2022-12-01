package com.example.testtecnicojairo.db.repository

import androidx.lifecycle.LiveData
import com.example.testtecnicojairo.db.AppDataBase
import com.example.testtecnicojairo.db.entity.SessionEntity
import com.example.testtecnicojairo.db.transformer.Transformer.converterSessionResultToEntity
import com.example.testtecnicojairo.splash.domain.models.SessionResult
import javax.inject.Inject

class SessionDBRepository @Inject constructor(
    private val appDataBase: AppDataBase
) {
    suspend fun insertSession(session: SessionResult): Long {
        return appDataBase.sessionDao()
            .insert(converterSessionResultToEntity(session))
    }

    suspend fun delete(session: SessionResult) {
        appDataBase.sessionDao().delete(converterSessionResultToEntity(session))
    }

    fun getAllSession(): LiveData<SessionEntity> {
        return appDataBase.sessionDao().getAllSession()
    }
}