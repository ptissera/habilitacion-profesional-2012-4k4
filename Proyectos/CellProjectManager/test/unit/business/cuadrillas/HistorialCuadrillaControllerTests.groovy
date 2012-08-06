package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(HistorialCuadrillaController)
@Mock(HistorialCuadrilla)
class HistorialCuadrillaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/historialCuadrilla/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.historialCuadrillaInstanceList.size() == 0
        assert model.historialCuadrillaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.historialCuadrillaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.historialCuadrillaInstance != null
        assert view == '/historialCuadrilla/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/historialCuadrilla/show/1'
        assert controller.flash.message != null
        assert HistorialCuadrilla.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/historialCuadrilla/list'


        populateValidParams(params)
        def historialCuadrilla = new HistorialCuadrilla(params)

        assert historialCuadrilla.save() != null

        params.id = historialCuadrilla.id

        def model = controller.show()

        assert model.historialCuadrillaInstance == historialCuadrilla
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/historialCuadrilla/list'


        populateValidParams(params)
        def historialCuadrilla = new HistorialCuadrilla(params)

        assert historialCuadrilla.save() != null

        params.id = historialCuadrilla.id

        def model = controller.edit()

        assert model.historialCuadrillaInstance == historialCuadrilla
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/historialCuadrilla/list'

        response.reset()


        populateValidParams(params)
        def historialCuadrilla = new HistorialCuadrilla(params)

        assert historialCuadrilla.save() != null

        // test invalid parameters in update
        params.id = historialCuadrilla.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/historialCuadrilla/edit"
        assert model.historialCuadrillaInstance != null

        historialCuadrilla.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/historialCuadrilla/show/$historialCuadrilla.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        historialCuadrilla.clearErrors()

        populateValidParams(params)
        params.id = historialCuadrilla.id
        params.version = -1
        controller.update()

        assert view == "/historialCuadrilla/edit"
        assert model.historialCuadrillaInstance != null
        assert model.historialCuadrillaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/historialCuadrilla/list'

        response.reset()

        populateValidParams(params)
        def historialCuadrilla = new HistorialCuadrilla(params)

        assert historialCuadrilla.save() != null
        assert HistorialCuadrilla.count() == 1

        params.id = historialCuadrilla.id

        controller.delete()

        assert HistorialCuadrilla.count() == 0
        assert HistorialCuadrilla.get(historialCuadrilla.id) == null
        assert response.redirectedUrl == '/historialCuadrilla/list'
    }
}
