import { ItemFactura } from '../../facturas/models/item-factura';

export class Almacen {
  id: string;
  nombre: string;
  direccion: string;
  items: Array<ItemFactura> = [];
  createAt: string;
}
