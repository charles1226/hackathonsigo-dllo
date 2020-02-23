import { Producto } from '../../facturas/models/producto';

export class ItemAlmacen {
  producto: Producto;
  cantidad: number = 1;
  importe: number;

  public calcularImporte(): number {
    return this.cantidad * this.producto.precio;
  }
}
