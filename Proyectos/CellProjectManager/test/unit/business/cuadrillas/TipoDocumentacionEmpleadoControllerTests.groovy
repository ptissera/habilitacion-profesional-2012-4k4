package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoDocumentacionEmpleadoController)
@Mock(TipoDocumentacionEmpleado)
class TipoDocumentacionEmpleadoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoDocumentacionEmpleado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoDocumentacionEmpleadoInstanceList.size() == 0
        assert model.tipoDocumentacionEmpleadoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.tipoDocumentacionEmpleadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoDocumentacionEmpleadoInstance != null
        assert view == '/tipoDocumentacionEmpleado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoDocumentacionEmpleado/show/1'
        assert controller.flash.message != null
        assert TipoDocumentacionEmpleado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionEmpleado/list'


        populateValidParams(params)
        def tipoDocumentacionEmpleado = new TipoDocumentacionEmpleado(params)

        assert tipoDocumentacionEmpleado.save() != null

        params.id = tipoDocumentacionEmpleado.id

        def model = controller.show()

        assert model.tipoDocumentacionEmpleadoInstance == tipoDocumentacionEmpleado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionEmpleado/list'


        populateValidParams(params)
        def tipoDocumentacionEmpleado = new TipoDocumentacionEmpleado(params)

        assert tipoDocumentacionEmpleado.save() != null

        params.id = tipoDocumentacionEmpleado.id

        def model = controller.edit()

        assert model.tipoDocumentacionEmpleadoInstance == tipoDocumentacionEmpleado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionEmpleado/list'

        response.reset()


        populateValidParams(params)
        def tipoDocumentacionEmpleado = new TipoDocumentacionEmpleado(params)

        assert tipoDocumentacionEmpleado.save() != null

        // test invalid parameters in update
        params.id = tipoDocumentacionEmpleado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoDocumentacionEmpleado/edit"
        assert model.tipoDocumentacionEmpleadoInstance != null

        tipoDocumentacionEmpleado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoDocumentacionEmpleado/show/$tipoDocumentacionEmpleado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoDocumentacionEmpleado.clearErrors()

        populateValidParams(params)
        params.id = tipoDocumentacionEmpleado.id
        params.version = -1
        controller.update()

        assert view == "/tipoDocumentacionEmpleado/edit"
        assert model.tipoDocumentacionEmpleadoInstance != null
        assert model.tipoDocumentacionEmpleadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoDocumentacionEmpleado/list'

        response.reset()

        populateValidParams(params)
        def tipoDocumentacionEmpleado = new TipoDocumentacionEmpleado(params)

        assert tipoDocumentacionEmpleado.save() != null
        assert TipoDocumentacionEmpleado.count() == 1

        params.id = tipoDocumentacionEmpleado.id

        controller.delete()

        assert TipoDocumentacionEmpleado.count() == 0
        assert TipoDocumentacionEmpleado.get(tipoDocumentacionEmpleado.id) == null
        assert response.redirectedUrl == '/tipoDocumentacionEmpleado/list'
    }
}
