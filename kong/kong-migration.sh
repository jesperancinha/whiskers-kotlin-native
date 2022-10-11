#!/bin/bash
kong migrations up &
kong migrations finish &