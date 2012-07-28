package business.core



import org.junit.*
import grails.test.mixin.*

@TestFor(LicitacionController)
@Mock(Licitacion)
class LicitacionControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/licitacion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.licitacionInstanceList.size() == 0
        assert model.licitacionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.licitacionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.licitacionInstance != null
        assert view == '/licitacion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/licitacion/show/1'
        assert controller.flash.message != null
        assert Licitacion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/licitacion/list'


        populateValidParams(params)
        def licitacion = new Licitacion(params)

        assert licitacion.save() != null

        params.id = licitacion.id

        def model = controller.show()

        assert model.licitacionInstance == licitacion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/licitacion/list'


        populateValidParams(params)
        def licitacion = new Licitacion(params)

        assert licitacion.save() != null

        params.id = licitacion.id

        def model = controller.edit()

        assert model.licitacionInstance == licitacion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/licitacion/list'

        response.reset()


        populateValidParams(params)
        def licitacion = new Licitacion(params)

        assert licitacion.save() != null

        // test invalid parameters in update
        params.id = licitacion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/licitacion/edit"
        assert model.licitacionInstance != null

        licitacion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/licitacion/show/$licitacion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        licitacion.clearErrors()

        populateValidParams(params)
        params.id = licitacion.id
        params.version = -1
        controller.update()

        assert view == "/licitacion/edit"
        assert model.licitacionInstance != null
        assert model.licitacionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/licitacion/list'

        response.reset()

        populateValidParams(params)
        def licitacion = new Licitacion(params)

        assert licitacion.save() != null
        assert Licitacion.count() == 1

        params.id = licitacion.id

        controller.delete()

        assert Licitacion.count() == 0
        assert Licitacion.get(licitacion.id) == null
        assert response.redirectedUrl == '/licitacion/list'
    }
}
