package business.core



import org.junit.*
import grails.test.mixin.*

@TestFor(ClienteController)
@Mock(Cliente)
class ClienteControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cliente/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.clienteInstanceList.size() == 0
        assert model.clienteInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.clienteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.clienteInstance != null
        assert view == '/cliente/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cliente/show/1'
        assert controller.flash.message != null
        assert Cliente.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'


        populateValidParams(params)
        def cliente = new Cliente(params)

        assert cliente.save() != null

        params.id = cliente.id

        def model = controller.show()

        assert model.clienteInstance == cliente
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'


        populateValidParams(params)
        def cliente = new Cliente(params)

        assert cliente.save() != null

        params.id = cliente.id

        def model = controller.edit()

        assert model.clienteInstance == cliente
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'

        response.reset()


        populateValidParams(params)
        def cliente = new Cliente(params)

        assert cliente.save() != null

        // test invalid parameters in update
        params.id = cliente.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cliente/edit"
        assert model.clienteInstance != null

        cliente.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cliente/show/$cliente.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cliente.clearErrors()

        populateValidParams(params)
        params.id = cliente.id
        params.version = -1
        controller.update()

        assert view == "/cliente/edit"
        assert model.clienteInstance != null
        assert model.clienteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cliente/list'

        response.reset()

        populateValidParams(params)
        def cliente = new Cliente(params)

        assert cliente.save() != null
        assert Cliente.count() == 1

        params.id = cliente.id

        controller.delete()

        assert Cliente.count() == 0
        assert Cliente.get(cliente.id) == null
        assert response.redirectedUrl == '/cliente/list'
    }
}
