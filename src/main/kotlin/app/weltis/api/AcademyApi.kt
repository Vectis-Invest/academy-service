package app.weltis.api

import app.weltis.model.Academy
import app.weltis.model.Material
import app.weltis.service.InitializeAcademyService
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/academy")
class AcademyApi
    @Inject
    constructor(
        private val academyService: InitializeAcademyService,
    ) {
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/init")
        fun initAcademy(academyData: Academy): Response {
            println(academyData)
            return Response.ok(academyData).build()
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/materials")
        fun testMaterials(materials: List<Material>): Response {
            println(materials)
            return Response.ok(materials).build()
        }
    }
