#!/bin/bash

pushd ../mirror-repo
  git fetch -p origin
  git push --mirror
popd

echo "Mirror repository updated"