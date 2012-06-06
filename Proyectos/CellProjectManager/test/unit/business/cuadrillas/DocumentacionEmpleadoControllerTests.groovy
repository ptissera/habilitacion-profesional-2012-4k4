package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(DocumentacionEmpleadoController)
@Mock(DocumentacionEmpleado)
class DocumentacionEmpleadoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/documentacionEmpleado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.documentacionEmpleadoInstanceList.size() == 0
        assert model.documentacionEmpleadoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.documentacionEmpleadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.documentacionEmpleadoInstance != null
        assert view == '/documentacionEmpleado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/documentacionEmpleado/show/1'
        assert controller.flash.message != null
        assert DocumentacionEmpleado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/documentacionEmpleado/list'


        populateValidParams(params)
        def documentacionEmpleado = new DocumentacionEmpleado(params)

        assert documentacionEmpleado.save() != null

        params.id = documentacionEmpleado.id

        def model = controller.show()

        assert model.documentacionEmpleadoInstance == documentacionEmpleado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/documentacionEmpleado/list'


        populateValidParams(params)
        def documentacionEmpleado = new DocumentacionEmpleado(params)

        assert documentacionEmpleado.save() != null

        params.id = documentacionEmpleado.id

        def model = controller.edit()

        assert model.documentacionEmpleadoInstance == documentacionEmpleado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/documentacionEmpleado/list'

        response.reset()


        populateValidParams(params)
        def documentacionEmpleado = new DocumentacionEmpleado(params)

        assert documentacionEmpleado.save() != null

        // test invalid parameters in update
        params.id = documentacionEmpleado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/documentacionEmpleado/edit"
        assert model.documentacionEmpleadoInstance != null

        documentacionEmpleado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/documentacionEmpleado/show/$documentacionEmpleado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        documentacionEmpleado.clearErrors()

        populateValidParams(params)
        params.id = documentacionEmpleado.id
        params.version = -1
        controller.update()

        assert view == "/documentacionEmpleado/edit"
        assert model.documentacionEmpleadoInstance != null
        assert model.documentacionEmpleadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/documentacionEmpleado/list'

        response.reset()

        populateValidParams(params)
        def documentacionEmpleado = new DocumentacionEmpleado(params)

        assert documentacionEmpleado.save() != null
        assert DocumentacionEmpleado.count() == 1

        params.id = documentacionEmpleado.id

        controller.delete()

        assert DocumentacionEmpleado.count() == 0
        assert DocumentacionEmpleado.get(documentacionEmpleado.id) == null
        assert response.redirectedUrl == '/documentacionEmpleado/list'
    }
}
