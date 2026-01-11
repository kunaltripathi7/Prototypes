flow:
user will read adn write from cache.
Read -> if cache has data it will give to user. if it doens't have it will get it from db.
write -> Write policy -> write in cache then in db.
Multithreading -> sequential operations. -> single thread will work.

clarifying -> which write policy? which eviction poilcies?, do you want read your own writes consistency? -> if yes prefer write through. should we support strong ordreing by key?
if multiple client writes to the same key -> which one wins? -> last write wins?. what happens if a cache don't find data? -> fallback strategy?

Clarifying:
Which consistency model do you want for clients (read-your-writes / eventual / strong)? — decides write policy and whether clients must see their own updates immediately.

Which write policy should we prefer: write-through, write-back, or write-around? — drives where the authoritative logic lives and how failures are handled.

What eviction policies are acceptable (LRU/LFU/TTL/size-based) and is memory bounded? — affects implementation choices and complexity of metadata.

How should concurrent writes to the same key be resolved (last-write-wins, versioning with CAS, or merge)? — important for multi-client correctness and API semantics.

On cache miss or cache failure, do we block the client until DB returns or serve stale data while refreshing in background? — affects latency and thread model.

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

Ensure tasks for the same key execute sequentially, while allowing different keys to run in parallel. -> key based executor

lets code




