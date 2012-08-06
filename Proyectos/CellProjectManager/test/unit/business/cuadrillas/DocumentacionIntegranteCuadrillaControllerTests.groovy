package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(DocumentacionIntegranteCuadrillaController)
@Mock(DocumentacionIntegranteCuadrilla)
class DocumentacionIntegranteCuadrillaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/documentacionIntegranteCuadrilla/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.documentacionIntegranteCuadrillaInstanceList.size() == 0
        assert model.documentacionIntegranteCuadrillaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.documentacionIntegranteCuadrillaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.documentacionIntegranteCuadrillaInstance != null
        assert view == '/documentacionIntegranteCuadrilla/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/documentacionIntegranteCuadrilla/show/1'
        assert controller.flash.message != null
        assert DocumentacionIntegranteCuadrilla.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/documentacionIntegranteCuadrilla/list'


        populateValidParams(params)
        def documentacionIntegranteCuadrilla = new DocumentacionIntegranteCuadrilla(params)

        assert documentacionIntegranteCuadrilla.save() != null

        params.id = documentacionIntegranteCuadrilla.id

        def model = controller.show()

        assert model.documentacionIntegranteCuadrillaInstance == documentacionIntegranteCuadrilla
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/documentacionIntegranteCuadrilla/list'


        populateValidParams(params)
        def documentacionIntegranteCuadrilla = new DocumentacionIntegranteCuadrilla(params)

        assert documentacionIntegranteCuadrilla.save() != null

        params.id = documentacionIntegranteCuadrilla.id

        def model = controller.edit()

        assert model.documentacionIntegranteCuadrillaInstance == documentacionIntegranteCuadrilla
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/documentacionIntegranteCuadrilla/list'

        response.reset()


        populateValidParams(params)
        def documentacionIntegranteCuadrilla = new DocumentacionIntegranteCuadrilla(params)

        assert documentacionIntegranteCuadrilla.save() != null

        // test invalid parameters in update
        params.id = documentacionIntegranteCuadrilla.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/documentacionIntegranteCuadrilla/edit"
        assert model.documentacionIntegranteCuadrillaInstance != null

        documentacionIntegranteCuadrilla.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/documentacionIntegranteCuadrilla/show/$documentacionIntegranteCuadrilla.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        documentacionIntegranteCuadrilla.clearErrors()

        populateValidParams(params)
        params.id = documentacionIntegranteCuadrilla.id
        params.version = -1
        controller.update()

        assert view == "/documentacionIntegranteCuadrilla/edit"
        assert model.documentacionIntegranteCuadrillaInstance != null
        assert model.documentacionIntegranteCuadrillaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/documentacionIntegranteCuadrilla/list'

        response.reset()

        populateValidParams(params)
        def documentacionIntegranteCuadrilla = new DocumentacionIntegranteCuadrilla(params)

        assert documentacionIntegranteCuadrilla.save() != null
        assert DocumentacionIntegranteCuadrilla.count() == 1

        params.id = documentacionIntegranteCuadrilla.id

        controller.delete()

        assert DocumentacionIntegranteCuadrilla.count() == 0
        assert DocumentacionIntegranteCuadrilla.get(documentacionIntegranteCuadrilla.id) == null
        assert response.redirectedUrl == '/documentacionIntegranteCuadrilla/list'
    }
}
