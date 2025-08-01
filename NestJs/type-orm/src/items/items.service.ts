import { Injectable } from '@nestjs/common';
import { CreateItemDto } from './dto/create-item.dto';
import { UpdateItemDto } from './dto/update-item.dto';
import { EntityManager, Repository } from 'typeorm';
import { Item } from './entities/item.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { Listing } from './entities/listing.entity';
import { Comments } from './entities/comments.entity';
import { Tag } from './entities/tag.entity';

@Injectable()
export class ItemsService {
  constructor(
    // means repo meths operate on the item table in the db (entity = table)
    @InjectRepository(Item)
    private readonly itemsRepository: Repository<Item>,
    private readonly entityManager: EntityManager,
  ) {}

  async create(createItemDto: CreateItemDto) {
    const listing = new Listing({
      ...createItemDto.listing,
      rating: 0,
    });
    const tags = createItemDto.tags.map(
      (createTagDto) => new Tag(createTagDto),
    );
    const item = new Item({
      ...createItemDto,
      comments: [],
      tags,
      listing,
    });
    await this.entityManager.save(item);
  }

  // return sends a response || if you want no response then -> await
  async findAll() {
    return this.itemsRepository.find();
  }

  async findOne(id: number) {
    await this.itemsRepository.findOne({
      where: { id },
      relations: {
        //populate
        listing: true,
        comments: true,
      },
    });
  }

  async update(id: number, updateItemDto: UpdateItemDto) {
    // const item = await this.itemsRepository.findOneBy({ id });
    // const comments = updateItemDto.comments.map(
    //   (createCommentDto) => new Comments(createCommentDto),
    // );
    // item.public = updateItemDto.public;
    // item.comments = comments;
    // await this.entityManager.save(item);

    // transactions (ACID)
    await this.entityManager.transaction(async (entityManager) => {
      const item = await this.itemsRepository.findOneBy({ id });
      const comments = updateItemDto.comments.map(
        (createCommentDto) => new Comments(createCommentDto),
      );
      item.public = updateItemDto.public;
      item.comments = comments;
      await entityManager.save(item);
      const tag = new Tag({ content: `${Math.random()}` });
      await entityManager.save(tag);
    });
  }

  async remove(id: number) {
    await this.itemsRepository.delete(id);
  }
}
