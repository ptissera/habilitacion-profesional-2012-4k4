package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(EstadoCuadrillaController)
@Mock(EstadoCuadrilla)
class EstadoCuadrillaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/estadoCuadrilla/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.estadoCuadrillaInstanceList.size() == 0
        assert model.estadoCuadrillaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.estadoCuadrillaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.estadoCuadrillaInstance != null
        assert view == '/estadoCuadrilla/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/estadoCuadrilla/show/1'
        assert controller.flash.message != null
        assert EstadoCuadrilla.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoCuadrilla/list'


        populateValidParams(params)
        def estadoCuadrilla = new EstadoCuadrilla(params)

        assert estadoCuadrilla.save() != null

        params.id = estadoCuadrilla.id

        def model = controller.show()

        assert model.estadoCuadrillaInstance == estadoCuadrilla
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoCuadrilla/list'


        populateValidParams(params)
        def estadoCuadrilla = new EstadoCuadrilla(params)

        assert estadoCuadrilla.save() != null

        params.id = estadoCuadrilla.id

        def model = controller.edit()

        assert model.estadoCuadrillaInstance == estadoCuadrilla
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoCuadrilla/list'

        response.reset()


        populateValidParams(params)
        def estadoCuadrilla = new EstadoCuadrilla(params)

        assert estadoCuadrilla.save() != null

        // test invalid parameters in update
        params.id = estadoCuadrilla.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/estadoCuadrilla/edit"
        assert model.estadoCuadrillaInstance != null

        estadoCuadrilla.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/estadoCuadrilla/show/$estadoCuadrilla.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        estadoCuadrilla.clearErrors()

        populateValidParams(params)
        params.id = estadoCuadrilla.id
        params.version = -1
        controller.update()

        assert view == "/estadoCuadrilla/edit"
        assert model.estadoCuadrillaInstance != null
        assert model.estadoCuadrillaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/estadoCuadrilla/list'

        response.reset()

        populateValidParams(params)
        def estadoCuadrilla = new EstadoCuadrilla(params)

        assert estadoCuadrilla.save() != null
        assert EstadoCuadrilla.count() == 1

        params.id = estadoCuadrilla.id

        controller.delete()

        assert EstadoCuadrilla.count() == 0
        assert EstadoCuadrilla.get(estadoCuadrilla.id) == null
        assert response.redirectedUrl == '/estadoCuadrilla/list'
    }
}
