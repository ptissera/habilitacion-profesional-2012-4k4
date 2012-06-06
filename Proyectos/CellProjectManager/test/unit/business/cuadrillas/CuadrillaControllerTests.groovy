package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(CuadrillaController)
@Mock(Cuadrilla)
class CuadrillaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cuadrilla/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cuadrillaInstanceList.size() == 0
        assert model.cuadrillaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.cuadrillaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cuadrillaInstance != null
        assert view == '/cuadrilla/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cuadrilla/show/1'
        assert controller.flash.message != null
        assert Cuadrilla.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cuadrilla/list'


        populateValidParams(params)
        def cuadrilla = new Cuadrilla(params)

        assert cuadrilla.save() != null

        params.id = cuadrilla.id

        def model = controller.show()

        assert model.cuadrillaInstance == cuadrilla
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cuadrilla/list'


        populateValidParams(params)
        def cuadrilla = new Cuadrilla(params)

        assert cuadrilla.save() != null

        params.id = cuadrilla.id

        def model = controller.edit()

        assert model.cuadrillaInstance == cuadrilla
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cuadrilla/list'

        response.reset()


        populateValidParams(params)
        def cuadrilla = new Cuadrilla(params)

        assert cuadrilla.save() != null

        // test invalid parameters in update
        params.id = cuadrilla.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cuadrilla/edit"
        assert model.cuadrillaInstance != null

        cuadrilla.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cuadrilla/show/$cuadrilla.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cuadrilla.clearErrors()

        populateValidParams(params)
        params.id = cuadrilla.id
        params.version = -1
        controller.update()

        assert view == "/cuadrilla/edit"
        assert model.cuadrillaInstance != null
        assert model.cuadrillaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cuadrilla/list'

        response.reset()

        populateValidParams(params)
        def cuadrilla = new Cuadrilla(params)

        assert cuadrilla.save() != null
        assert Cuadrilla.count() == 1

        params.id = cuadrilla.id

        controller.delete()

        assert Cuadrilla.count() == 0
        assert Cuadrilla.get(cuadrilla.id) == null
        assert response.redirectedUrl == '/cuadrilla/list'
    }
}
