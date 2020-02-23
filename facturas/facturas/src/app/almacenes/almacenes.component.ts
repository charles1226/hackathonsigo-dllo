import { Component, OnInit } from '@angular/core';

import { Almacen } from './models/almacen';
import { ModalService } from '../clientes/detalle/modal.service';
import swal from 'sweetalert2';
import { tap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';
import { AlmacenService } from './almacen.service';

@Component({
  selector: 'app-almacenes',
  templateUrl: './almacenes.component.html',
  styleUrls: ['./almacenes.component.css']
})
export class AlmacenesComponent implements OnInit {

  almacenes: Almacen[];
  paginador: any;
  almacenSeleccionado: Almacen;

  constructor(private almacenService: AlmacenService,
    public modalService: ModalService,
    public authService: AuthService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(params => {
      let page: number = +params.get('page');

      if (!page) {
        page = 0;
      }

      this.almacenService.getAlmacenes(page)
        .pipe(
          tap(response => {
            console.log('AlmacenesComponent: tap 3');
            (response.data as Almacen[]).forEach(almacen => console.log(almacen.nombre));
          })
        ).subscribe(response => {
          console.log(response);
          this.almacenes = response.data as Almacen[];
          this.paginador = {
            number: response.currentPage,
            totalPages: Math.ceil(response.count/5)
          };
        });
    });
  }

  delete(almacen: Almacen): void {
    swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar al almacen ${almacen.nombre} ${almacen.direccion}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      buttonsStyling: false,
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.almacenService.delete(almacen.id).subscribe(
          () => {
            this.almacenes = this.almacenes.filter(cli => cli !== almacen)
            swal.fire(
              'Almacen Eliminado!',
              `Almacen ${almacen.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )

      }
    });
  }

  abrirModal(almacen: Almacen) {
    this.almacenSeleccionado = almacen;
    this.modalService.abrirModal();
  }

}
