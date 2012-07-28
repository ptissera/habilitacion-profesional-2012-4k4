package business.core



import org.junit.*
import grails.test.mixin.*

@TestFor(EstadoProyectoController)
@Mock(EstadoProyecto)
class EstadoProyectoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/estadoProyecto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.estadoProyectoInstanceList.size() == 0
        assert model.estadoProyectoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.estadoProyectoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.estadoProyectoInstance != null
        assert view == '/estadoProyecto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/estadoProyecto/show/1'
        assert controller.flash.message != null
        assert EstadoProyecto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoProyecto/list'


        populateValidParams(params)
        def estadoProyecto = new EstadoProyecto(params)

        assert estadoProyecto.save() != null

        params.id = estadoProyecto.id

        def model = controller.show()

        assert model.estadoProyectoInstance == estadoProyecto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoProyecto/list'


        populateValidParams(params)
        def estadoProyecto = new EstadoProyecto(params)

        assert estadoProyecto.save() != null

        params.id = estadoProyecto.id

        def model = controller.edit()

        assert model.estadoProyectoInstance == estadoProyecto
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/estadoProyecto/list'

        response.reset()


        populateValidParams(params)
        def estadoProyecto = new EstadoProyecto(params)

        assert estadoProyecto.save() != null

        // test invalid parameters in update
        params.id = estadoProyecto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/estadoProyecto/edit"
        assert model.estadoProyectoInstance != null

        estadoProyecto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/estadoProyecto/show/$estadoProyecto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        estadoProyecto.clearErrors()

        populateValidParams(params)
        params.id = estadoProyecto.id
        params.version = -1
        controller.update()

        assert view == "/estadoProyecto/edit"
        assert model.estadoProyectoInstance != null
        assert model.estadoProyectoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/estadoProyecto/list'

        response.reset()

        populateValidParams(params)
        def estadoProyecto = new EstadoProyecto(params)

        assert estadoProyecto.save() != null
        assert EstadoProyecto.count() == 1

        params.id = estadoProyecto.id

        controller.delete()

        assert EstadoProyecto.count() == 0
        assert EstadoProyecto.get(estadoProyecto.id) == null
        assert response.redirectedUrl == '/estadoProyecto/list'
    }
}
