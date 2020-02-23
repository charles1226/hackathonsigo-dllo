import { Component, OnInit } from '@angular/core';

import { Almacen } from './models/almacen';
import { AlmacenService } from './almacen.service';
import { Router, ActivatedRoute } from '@angular/router';

import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, flatMap } from 'rxjs/operators';
import { Producto } from '../facturas/models/producto';
import swal from 'sweetalert2';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { ItemAlmacen } from './models/item-almacen';

@Component({
  selector: 'app-almacen-form',
  templateUrl: './almacen-form.component.html'
})
export class AlmacenFormComponent implements OnInit {

  public almacen: Almacen = new Almacen();
  titulo: string = "Crear Almacen";
  autocompleteControl = new FormControl();
  productosFiltrados: Observable<Producto[]>;

  errores: string[];

  constructor(private almacenService: AlmacenService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = params.get('id');
      if (id) {
        this.almacenService.getAlmacen(id).subscribe((almacen) => this.almacen = almacen);
      }
    });

    this.productosFiltrados = this.autocompleteControl.valueChanges
      .pipe(
        map(value => typeof value === 'string' ? value : value.nombre),
        flatMap(value => value ? this._filter(value) : [])
      );
  }

  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();

    return this.almacenService.filtrarProductos(filterValue);
  }

  create(almacenForm): void {
    console.log(this.almacen);
    if (almacenForm.form.valid && this.almacen.items.length > 0) {
      this.almacenService.create(this.almacen).subscribe(almacen => {
        swal.fire(this.titulo, `Almacen ${almacen.nombre} creada con éxito!`, 'success');
        this.router.navigate(['/almacenes']);
      });
    }
  }

  update(almacenForm): void {
    console.log(this.almacen);
    if (almacenForm.form.valid && this.almacen.items.length > 0) {
      this.almacenService.create(this.almacen).subscribe(almacen => {
        swal.fire(this.titulo, `Almacen ${almacen.nombre} creada con éxito!`, 'success');
        this.router.navigate(['/almacenes']);
      });
    }
  }

  mostrarNombre(producto?: Producto): string | undefined {
    return producto ? producto.nombre : undefined;
  }

  seleccionarProducto(event: MatAutocompleteSelectedEvent): void {
    let producto = event.option.value as Producto;
    console.log(producto);

    if (this.existeItem(producto.id)) {
      this.incrementaCantidad(producto.id);
    } else {
      let nuevoItem = new ItemAlmacen();
      nuevoItem.producto = producto;
      this.almacen.items.push(nuevoItem);
    }

    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();

  }

  actualizarCantidad(id: string, event: any): void {
    let cantidad: number = event.target.value as number;

    if (cantidad == 0) {
      return this.eliminarItemAlmacen(id);
    }

    this.almacen.items = this.almacen.items.map((item: ItemAlmacen) => {
      if (id === item.producto.id) {
        item.cantidad = cantidad;
      }
      return item;
    });
  }

  eliminarItemAlmacen(id: string): void {
    this.almacen.items = this.almacen.items.filter((item: ItemAlmacen) => id !== item.producto.id);
  }

  incrementaCantidad(id: string): void {
    this.almacen.items = this.almacen.items.map((item: ItemAlmacen) => {
      if (id === item.producto.id) {
        ++item.cantidad;
      }
      return item;
    });
  }

  existeItem(id: string): boolean {
    let existe = false;
    this.almacen.items.forEach((item: ItemAlmacen) => {
      if (id === item.producto.id) {
        existe = true;
      }
    });
    return existe;
  }

}
