flow:
user will read adn write from cache.
Read -> if cache has data it will give to user. if it doens't have it will get it from db.
write -> Write policy -> write in cache then in db.
Multithreading -> sequential operations. -> single thread will work.

clarifying -> which write policy? which eviction poilcies?, do you want read your own writes consistency? -> if yes prefer write through. should we support strong ordreing by key?
if multiple client writes to the same key -> which one wins? -> last write wins?. what happens if a cache don't find data? -> fallback strategy?

Write Policy write into db and cache.
Eviction Algorithm use for maintating the cache.
client gettting the data from cache -> if not found -> use eviction algorithm if full.
client writing the data into cache. -> write policy -> write into db and send back.
Read after write consistency -> single thread executors.


Derving entities:
CacheStorage, DBStorage, WritePolicy, EvictionAlgorithm

multiple entities -> Cache & eviction algorithm -> KeyBasedExceutor/service -> handle

Cache -> will be the orchestrator/controller

main -> doing all of it.

concurrency -> ???????

Write policy -> strategy pattern
eviction algoritm(DLL, Node) -> strategy pattern
CacheStorage -> can be of many types-> Redis and all
DB storage -> many types -> mongo/postgres
enums -> no.

lets code




