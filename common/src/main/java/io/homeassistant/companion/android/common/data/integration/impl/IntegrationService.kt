package io.homeassistant.companion.android.common.data.integration.impl

import io.homeassistant.companion.android.common.data.integration.ZoneAttributes
import io.homeassistant.companion.android.common.data.integration.impl.entities.CheckRateLimits
import io.homeassistant.companion.android.common.data.integration.impl.entities.EntityResponse
import io.homeassistant.companion.android.common.data.integration.impl.entities.IntegrationRequest
import io.homeassistant.companion.android.common.data.integration.impl.entities.RateLimitRequest
import io.homeassistant.companion.android.common.data.integration.impl.entities.RegisterDeviceRequest
import io.homeassistant.companion.android.common.data.integration.impl.entities.RegisterDeviceResponse
import io.homeassistant.companion.android.common.data.websocket.impl.entities.GetConfigResponse
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

interface IntegrationService {
    @POST
    suspend fun registerDevice(
        @Url url: HttpUrl,
        @Header("Authorization") auth: String,
        @HeaderMap headers: Map<String, String>,
        @Body request: RegisterDeviceRequest
    ): RegisterDeviceResponse

    @GET
    suspend fun getState(
        @Url url: HttpUrl,
        @Header("Authorization") auth: String,
        @HeaderMap headers: Map<String, String>
    ): EntityResponse<Map<String, Any>>

    @POST
    suspend fun callWebhook(
        @Url url: HttpUrl,
        @HeaderMap headers: Map<String, String>,
        @Body request: IntegrationRequest
    ): Response<ResponseBody>

    @POST
    suspend fun getTemplate(
        @Url url: HttpUrl,
        @HeaderMap headers: Map<String, String>,
        @Body request: IntegrationRequest
    ): Map<String, String>

    @POST
    suspend fun getZones(
        @Url url: HttpUrl,
        @HeaderMap headers: Map<String, String>,
        @Body request: IntegrationRequest
    ): Array<EntityResponse<ZoneAttributes>>

    @POST
    suspend fun getConfig(
        @Url url: HttpUrl,
        @HeaderMap headers: Map<String, String>,
        @Body request: IntegrationRequest
    ): GetConfigResponse

    @POST
    suspend fun getRateLimit(
        @Url url: String,
        @Body request: RateLimitRequest
    ): CheckRateLimits

    @POST
    suspend fun updateSensors(
        @Url url: HttpUrl,
        @HeaderMap headers: Map<String, String>,
        @Body request: IntegrationRequest
    ): Map<String, Map<String, Any>>
}
