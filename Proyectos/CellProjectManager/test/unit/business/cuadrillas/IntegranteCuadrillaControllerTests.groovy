package business.cuadrillas



import org.junit.*
import grails.test.mixin.*

@TestFor(IntegranteCuadrillaController)
@Mock(IntegranteCuadrilla)
class IntegranteCuadrillaControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/integranteCuadrilla/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.integranteCuadrillaInstanceList.size() == 0
        assert model.integranteCuadrillaInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.integranteCuadrillaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.integranteCuadrillaInstance != null
        assert view == '/integranteCuadrilla/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/integranteCuadrilla/show/1'
        assert controller.flash.message != null
        assert IntegranteCuadrilla.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/integranteCuadrilla/list'


        populateValidParams(params)
        def integranteCuadrilla = new IntegranteCuadrilla(params)

        assert integranteCuadrilla.save() != null

        params.id = integranteCuadrilla.id

        def model = controller.show()

        assert model.integranteCuadrillaInstance == integranteCuadrilla
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/integranteCuadrilla/list'


        populateValidParams(params)
        def integranteCuadrilla = new IntegranteCuadrilla(params)

        assert integranteCuadrilla.save() != null

        params.id = integranteCuadrilla.id

        def model = controller.edit()

        assert model.integranteCuadrillaInstance == integranteCuadrilla
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/integranteCuadrilla/list'

        response.reset()


        populateValidParams(params)
        def integranteCuadrilla = new IntegranteCuadrilla(params)

        assert integranteCuadrilla.save() != null

        // test invalid parameters in update
        params.id = integranteCuadrilla.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/integranteCuadrilla/edit"
        assert model.integranteCuadrillaInstance != null

        integranteCuadrilla.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/integranteCuadrilla/show/$integranteCuadrilla.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        integranteCuadrilla.clearErrors()

        populateValidParams(params)
        params.id = integranteCuadrilla.id
        params.version = -1
        controller.update()

        assert view == "/integranteCuadrilla/edit"
        assert model.integranteCuadrillaInstance != null
        assert model.integranteCuadrillaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/integranteCuadrilla/list'

        response.reset()

        populateValidParams(params)
        def integranteCuadrilla = new IntegranteCuadrilla(params)

        assert integranteCuadrilla.save() != null
        assert IntegranteCuadrilla.count() == 1

        params.id = integranteCuadrilla.id

        controller.delete()

        assert IntegranteCuadrilla.count() == 0
        assert IntegranteCuadrilla.get(integranteCuadrilla.id) == null
        assert response.redirectedUrl == '/integranteCuadrilla/list'
    }
}
