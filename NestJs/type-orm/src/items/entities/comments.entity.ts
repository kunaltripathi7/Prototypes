import { AbstractEntity } from '../../database/abstract-entity';
import { Column, Entity, ManyToOne } from 'typeorm';
import { Item } from './item.entity';

@Entity()
export class Comments extends AbstractEntity<Comments> {
  @Column()
  content: string;

  // what type? , where its located
  @ManyToOne(() => Item, (item) => item.comments)
  item: Item;
}
