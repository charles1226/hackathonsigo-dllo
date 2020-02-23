import { ItemFactura } from './item-factura';
import { Cliente } from '../../clientes/cliente';

export class Factura {
  id: string;
  descripcion: string;
  observacion: string;
  items: Array<ItemFactura> = [];
  cliente: Cliente;
  iva: number;
  total: number;
  createAt: string;

  calcularGranTotal(): number {
    this.total = 0;
    this.items.forEach((item: ItemFactura) => {
      this.total += item.calcularImporte();
    });
    return this.total;
  }
}
