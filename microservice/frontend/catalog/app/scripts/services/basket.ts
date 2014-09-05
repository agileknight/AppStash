interface IBasketItem {
    uuid: String;
    product: IProduct;
}


interface IBasketService {
    add(product:IProduct);
    remove(uuid: String);
    getAll():IBasketItem[];
}

class BasketService implements IBasketService {

    private BASKET_ITEMS_KEY = 'basketItems';

    static $inject = ['localStorageService'];

    constructor(private localStorageService: ng.localStorage.ILocalStorageService) {}

    add(product:IProduct) {
        var uuid:String = this.newUUID();
        var basketItem: IBasketItem = {uuid: uuid, product : product};

        var basketItems: IBasketItem[] = this.getAll();
        basketItems.push(basketItem);

        this.localStorageService.set(this.BASKET_ITEMS_KEY, basketItems)
    }

    remove(uuid:String) {
        var basketItems: IBasketItem[] = this.getAll();
        basketItems = _.without(basketItems, _.findWhere(basketItems, {uuid: uuid}));
        this.localStorageService.set(this.BASKET_ITEMS_KEY, basketItems)
    }

    getAll():IBasketItem[] {
        return this.localStorageService.get(this.BASKET_ITEMS_KEY) || [];
    }

    private newUUID():String {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
    }
}

eshop.service('basketService', BasketService);