import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NnComponent } from './nn.component';

describe('NnComponent', () => {
  let component: NnComponent;
  let fixture: ComponentFixture<NnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NnComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
