import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FlavorFacade } from 'src/app/store/flavor-facade';
import { CallState } from 'src/app/store/flavor-models';


@Component({
  selector: 'app-list-flavor',
  templateUrl: './list-flavor.component.html',
  styleUrls: ['./list-flavor.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ListFlavorComponent {

  protected CallState = CallState;

  flavorsVm$ = this.flavorFacade.flavorsVm$;

  constructor(
    private flavorFacade: FlavorFacade
  ) { }


}
