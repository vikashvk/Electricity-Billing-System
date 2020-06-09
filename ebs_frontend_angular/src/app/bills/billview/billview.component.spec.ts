import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillviewComponent } from './billview.component';

describe('BillviewComponent', () => {
  let component: BillviewComponent;
  let fixture: ComponentFixture<BillviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
