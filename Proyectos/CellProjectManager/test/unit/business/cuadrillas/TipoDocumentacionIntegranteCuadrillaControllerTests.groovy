package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoDocumentacionIntegranteCuadrillaController)
@Mock(TipoDocumentacionIntegranteCuadrilla)
class TipoDocumentacionIntegranteCuadrillaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoDocumentacionIntegranteCuadrilla/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoDocumentacionIntegranteCuadrillaInstanceList.size() == 0
        assert model.tipoDocumentacionIntegranteCuadrillaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tipoDocumentacionIntegranteCuadrillaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoDocumentacionIntegranteCuadrillaInstance != null
        assert view == '/tipoDocumentacionIntegranteCuadrilla/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoDocumentacionIntegranteCuadrilla/show/1'
        assert controller.flash.message != null
        assert TipoDocumentacionIntegranteCuadrilla.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionIntegranteCuadrilla/list'


        populateValidParams(params)
        def tipoDocumentacionIntegranteCuadrilla = new TipoDocumentacionIntegranteCuadrilla(params)

        assert tipoDocumentacionIntegranteCuadrilla.save() != null

        params.id = tipoDocumentacionIntegranteCuadrilla.id

        def model = controller.show()

        assert model.tipoDocumentacionIntegranteCuadrillaInstance == tipoDocumentacionIntegranteCuadrilla
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionIntegranteCuadrilla/list'


        populateValidParams(params)
        def tipoDocumentacionIntegranteCuadrilla = new TipoDocumentacionIntegranteCuadrilla(params)

        assert tipoDocumentacionIntegranteCuadrilla.save() != null

        params.id = tipoDocumentacionIntegranteCuadrilla.id

        def model = controller.edit()

        assert model.tipoDocumentacionIntegranteCuadrillaInstance == tipoDocumentacionIntegranteCuadrilla
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionIntegranteCuadrilla/list'

        response.reset()


        populateValidParams(params)
        def tipoDocumentacionIntegranteCuadrilla = new TipoDocumentacionIntegranteCuadrilla(params)

        assert tipoDocumentacionIntegranteCuadrilla.save() != null

        // test invalid parameters in update
        params.id = tipoDocumentacionIntegranteCuadrilla.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoDocumentacionIntegranteCuadrilla/edit"
        assert model.tipoDocumentacionIntegranteCuadrillaInstance != null

        tipoDocumentacionIntegranteCuadrilla.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoDocumentacionIntegranteCuadrilla/show/$tipoDocumentacionIntegranteCuadrilla.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoDocumentacionIntegranteCuadrilla.clearErrors()

        populateValidParams(params)
        params.id = tipoDocumentacionIntegranteCuadrilla.id
        params.version = -1
        controller.update()

        assert view == "/tipoDocumentacionIntegranteCuadrilla/edit"
        assert model.tipoDocumentacionIntegranteCuadrillaInstance != null
        assert model.tipoDocumentacionIntegranteCuadrillaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionIntegranteCuadrilla/list'

        response.reset()

        populateValidParams(params)
        def tipoDocumentacionIntegranteCuadrilla = new TipoDocumentacionIntegranteCuadrilla(params)

        assert tipoDocumentacionIntegranteCuadrilla.save() != null
        assert TipoDocumentacionIntegranteCuadrilla.count() == 1

        params.id = tipoDocumentacionIntegranteCuadrilla.id

        controller.delete()

        assert TipoDocumentacionIntegranteCuadrilla.count() == 0
        assert TipoDocumentacionIntegranteCuadrilla.get(tipoDocumentacionIntegranteCuadrilla.id) == null
        assert response.redirectedUrl == '/tipoDocumentacionIntegranteCuadrilla/list'
    }
}
