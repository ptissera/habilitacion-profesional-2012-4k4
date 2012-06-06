package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(EmpleadoController)
@Mock(Empleado)
class EmpleadoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/empleado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.empleadoInstanceList.size() == 0
        assert model.empleadoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.empleadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.empleadoInstance != null
        assert view == '/empleado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/empleado/show/1'
        assert controller.flash.message != null
        assert Empleado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empleado/list'


        populateValidParams(params)
        def empleado = new Empleado(params)

        assert empleado.save() != null

        params.id = empleado.id

        def model = controller.show()

        assert model.empleadoInstance == empleado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empleado/list'


        populateValidParams(params)
        def empleado = new Empleado(params)

        assert empleado.save() != null

        params.id = empleado.id

        def model = controller.edit()

        assert model.empleadoInstance == empleado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empleado/list'

        response.reset()


        populateValidParams(params)
        def empleado = new Empleado(params)

        assert empleado.save() != null

        // test invalid parameters in update
        params.id = empleado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/empleado/edit"
        assert model.empleadoInstance != null

        empleado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/empleado/show/$empleado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        empleado.clearErrors()

        populateValidParams(params)
        params.id = empleado.id
        params.version = -1
        controller.update()

        assert view == "/empleado/edit"
        assert model.empleadoInstance != null
        assert model.empleadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/empleado/list'

        response.reset()

        populateValidParams(params)
        def empleado = new Empleado(params)

        assert empleado.save() != null
        assert Empleado.count() == 1

        params.id = empleado.id

        controller.delete()

        assert Empleado.count() == 0
        assert Empleado.get(empleado.id) == null
        assert response.redirectedUrl == '/empleado/list'
    }
}
