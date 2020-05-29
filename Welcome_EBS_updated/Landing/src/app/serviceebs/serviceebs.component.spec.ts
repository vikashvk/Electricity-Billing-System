import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceebsComponent } from './serviceebs.component';

describe('ServiceebsComponent', () => {
  let component: ServiceebsComponent;
  let fixture: ComponentFixture<ServiceebsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceebsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceebsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
