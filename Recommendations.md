# whiskers-kotlin-native

Recommendations for:

## 1. Linux

```shell
echo fs.inotify.max_user_watches=33554432 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
cat /proc/sys/fs/inotify/max_user_watches
```

More info on: [jeorg-cloud-testdrives @ ubuntu](https://jesperancinha.github.io/jeorg-cloud-test-drives/system/ubuntu/)

## 2. Mac-OS

Coming soon...
