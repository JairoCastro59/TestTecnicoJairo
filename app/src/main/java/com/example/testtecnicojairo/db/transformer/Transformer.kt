package com.example.testtecnicojairo.db.transformer

import com.example.testtecnicojairo.db.entity.SessionEntity
import com.example.testtecnicojairo.splash.domain.models.SessionResult

object Transformer {

    fun converterSessionResultToEntity(sessionResult: SessionResult): SessionEntity {
        return SessionEntity(
            success = sessionResult.success,
            session_id = sessionResult.session_id
        )
    }

    fun converterSessionEntitytoSession(sessionEntity: SessionEntity): SessionResult {
        return SessionResult(
            success =  sessionEntity.success,
            session_id = sessionEntity.session_id
        )
    }
}