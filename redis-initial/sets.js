const Sets = (redis) => {
  redis.sadd("groceries:dairies", "milk", "cheese", "yogourt");
  redis.sadd("groceries:seafod", "salmon", "calamari", "lobster");
  redis.sadd("groceries:fruits", "apples", "grapes", "orange");

  redis.smembers("groceries:fruits", (err, result) => {
    console.log(result);
  });

  redis.spop("groceries:fruits");

  redis.smembers("groceries:fruits", (err, result) => {
    console.log(result);
  });
};

export default Sets;
